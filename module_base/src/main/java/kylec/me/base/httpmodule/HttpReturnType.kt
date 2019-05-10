package kylec.me.base.httpmodule

/**
 * HTTP request returns json type
 *
 * Created by KYLE on 2019/5/8 - 15:19
 */
class HttpReturnType<T>(
    var data: T,
    var errorCode: Int,
    var errorMsg: String
)

// errorCode = 0  success  else failed

/*
    注册：

    failed:
    {
    "data": null,
    "errorCode": -1,
    "errorMsg": "用户名已经被注册！"
    }

    success:
    {
    "data": {
        "chapterTops": [],
        "collectIds": [],
        "email": "",
        "icon": "",
        "id": 23169,
        "password": "",
        "token": "",
        "type": 0,
        "username": "FairHand"
    },
    "errorCode": 0,
    "errorMsg": ""
    }


    登录：

    failed:
    {
    "data": null,
    "errorCode": -1,
    "errorMsg": "账号密码不匹配！"
    }

    success:
    {
    "data": {
        "chapterTops": [],
        "collectIds": [],
        "email": "",
        "icon": "",
        "id": 23169,
        "password": "",
        "token": "",
        "type": 0,
        "username": "FairHand"
    },
    "errorCode": 0,
    "errorMsg": ""
    }
 */
