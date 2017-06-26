package com.github.VP.F.Person;

import com.github.B.BaseFragment;
import com.github.R;

/**
 * Created by axnshy on 2017/6/21.
 */

public class PersonFragment extends BaseFragment<IPerson,PersonFragmentPresenter> {

    @Override
    public int layoutId() {
        return R.layout.activity_person;
    }

    @Override
    public PersonFragmentPresenter createPresenter() {
        return new PersonFragmentPresenter();
    }
}
