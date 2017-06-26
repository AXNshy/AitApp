package com.github.M.Entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by axnshy on 2017/6/21.
 */

public class User {
    /*
    * "login": "sr",
      "id": 90,
      "avatar_url": "https://avatars3.githubusercontent.com/u/90?v=3",
      "gravatar_id": "",
      "url": "https://api.github.com/users/sr",
      "html_url": "https://github.com/sr",
      "followers_url": "https://api.github.com/users/sr/followers",
      "following_url": "https://api.github.com/users/sr/following{/other_user}",
      "gists_url": "https://api.github.com/users/sr/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/sr/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/sr/subscriptions",
      "organizations_url": "https://api.github.com/users/sr/orgs",
      "repos_url": "https://api.github.com/users/sr/repos",
      "events_url": "https://api.github.com/users/sr/events{/privacy}",
      "received_events_url": "https://api.github.com/users/sr/received_events",
      "type": "User",
      "site_admin": false
    *
    * */

    private String login;

    private int id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    private String type;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getType() {
        return type;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setType(String type) {
        this.type = type;
    }
}
