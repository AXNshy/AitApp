/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.testing.jacoco.tasks;

import org.gradle.api.Action;
import org.gradle.api.Incubating;
import org.gradle.api.Task;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.file.FileCollection;
import org.gradle.api.internal.project.IsolatedAntBuilder;
import org.gradle.api.provider.PropertyState;
import org.gradle.api.provider.Provider;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.PathSensitive;
import org.gradle.api.tasks.PathSensitivity;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskCollection;
import org.gradle.internal.jacoco.AntJacocoMerge;
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension;

import javax.inject.Inject;
import java.io.File;

/**
 * Task to merge multiple execution data files into one.
 */
@CacheableTask
@Incubating
public class JacocoMerge extends JacocoBase {

    private FileCollection executionData;
    private final PropertyState<File> destinationFile = getProject().property(File.class);

    /**
     * Collection of execution data files to merge.
     */
    @PathSensitive(PathSensitivity.RELATIVE)
    @InputFiles
    public FileCollection getExecutionData() {
        return executionData;
    }

    public void setExecutionData(FileCollection executionData) {
        this.executionData = executionData;
    }

    /**
     * File to write merged execution data to.
     */
    @OutputFile
    public File getDestinationFile() {
        return destinationFile.get();
    }

    public void setDestinationFile(File destinationFile) {
        this.destinationFile.set(destinationFile);
    }

    public void setDestinationFile(Provider<File> destinationFile) {
        this.destinationFile.set(destinationFile);
    }

    @Inject
    protected IsolatedAntBuilder getAntBuilder() {
        throw new UnsupportedOperationException();
    }

    @TaskAction
    public void merge() {
        new AntJacocoMerge(getAntBuilder()).execute(getJacocoClasspath(), getExecutionData(), getDestinationFile());
    }

    /**
     * Adds execution data files to be merged.
     *
     * @param files one or more files to merge
     */
    public void executionData(Object... files) {
        if (executionData == null) {
            executionData = getProject().files(files);
        } else {
            executionData = executionData.plus(getProject().files(files));
        }
    }

    /**
     * Adds execution data generated by a task to the list of those to merge. Only tasks with a {@link JacocoTaskExtension} will be included; all others will be ignored.
     *
     * @param tasks one or more tasks to merge
     */
    public void executionData(Task... tasks) {
        for (Task task : tasks) {
            JacocoTaskExtension extension = task.getExtensions().findByType(JacocoTaskExtension.class);
            if (extension != null) {
                ConfigurableFileCollection files = getProject().files(extension.getDestinationFile());
                files.builtBy(task);
                executionData(files);
            }
        }
    }

    /**
     * Adds execution data generated by the given tasks to the list of those merged. Only tasks with a {@link JacocoTaskExtension} will be included; all others will be ignored.
     *
     * @param tasks one or more tasks to merge
     */
    public void executionData(TaskCollection tasks) {
        tasks.all(new Action<Task>() {
            @Override
            public void execute(Task task) {
                executionData(task);
            }
        });
    }
}
