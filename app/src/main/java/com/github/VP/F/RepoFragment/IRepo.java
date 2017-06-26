package com.github.VP.F.RepoFragment;

import com.github.B.I.IBaseView;
import com.github.M.Entity.Repository;

import java.util.List;

/**
 * Created by axnshy on 2017/6/21.
 */

public interface IRepo extends IBaseView{


    void setRefreshing(boolean refresh);

    void setLoading(boolean load);

    void update(List<Repository> repositories,int clear);

    void clearData();
}
