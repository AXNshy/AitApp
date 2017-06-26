package com.github.M.Http;

/**
 * Created by axnshy on 2017/6/23.
 */

public interface Constants {




    /*
    * api API接口
    * */



    /*
    *
    * authenticate
    * */
    String  AUTHENTICATION_TOKEN = "token:";

    /*
    * repositories
    * */
    String REPOSITORIES = "repositories";


    /*
    * users
    * */
    String USERS = "users";


    /*
    * search repository  搜索接口的常量
    * */

    String SEARCH_REPOSITORY = "search/repositories";
    String QUALIFIER = "q";
    String QUALIFIER_SEPERATER = "+";
    String QUALIFIER_LAN= "language:";
    String QUALIFIER_LAN_JAVA= "java";
    String QUALIFIER_LAN_C= "c";
    String QUALIFIER_LAN_C_PLUS= "c++";
    String QUALIFIER_SIZE= "size:";


    String SORT = "sort";
    String SORT_STAR = "stars";
    String SORT_FORKS = "forks";
    String SORT_UPDATES = "updated";


    String ORDER = "order";
    String ORDER_DESC = "desc";
    String ORDER_ASC = "asc";

    /*
    * fork
    * */


}
