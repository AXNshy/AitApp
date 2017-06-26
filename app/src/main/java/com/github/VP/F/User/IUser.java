package com.github.VP.F.User;

import com.github.B.I.IBaseView;
import com.github.M.Entity.User;

import java.util.List;

/**
 * Created by axnshy on 2017/6/21.
 */

public interface IUser extends IBaseView{

    /*
    * 从服务器获取到用户列表并显示到界面上
    *
    * */
    void update(List<User> users,int clear);
}
