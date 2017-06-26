package com.github;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
       /* assertEquals(4, 2 + 2);

        RetrofitClient.getInstance().searchRepositories("rxjava","stars","desc")
                .subscribe(new Consumer<BaseResponseSearch<Repository>>() {
                    @Override
                    public void accept(@NonNull BaseResponseSearch<Repository> response) throws Exception {
                        System.out.print(response);

                    }
                });*/

        cat cat1 =new cat();
        cat cat2 =new cat();
        cat cat3 =new cat();
        cat2.change();
        cat3.change();
        System.out.println(cat1.getName());
        System.out.println(cat2.getName());
        System.out.println(cat3.getName());
    }
}