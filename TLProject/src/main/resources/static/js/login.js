
// 登录表单提交事件处理函数
function handleSubmit(event) {
    event.preventDefault(); // 阻止表单默认提交行为
    // 获取表单数据
    var formData = new FormData(event.target);

    //


    //验证用户名、密码和验证码
    var username = formData.get('username');
    var password = formData.get('password');
    var captcha = formData.get('captcha');

    if (username === '' || password === '' || captcha === '') {
        alert('用户名、密码或验证码不能为空！');
        return;
    }

    var userData = {
        username: username,
        password: password,
        captcha: captcha
    };

    // 发送异步请求
    fetch('loginfun', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.resultCode === 200) {
                alert('登录成功！');
                window.location.href = '/TLProject/test/index';
            } else {
                // 用户不存在或密码错误、验证码错误，刷新验证码
                refreshCaptcha();
                alert(data.message);
            }
        })
        .catch(error => alert.error('Error:', error));
}


// 刷新验证码
function refreshCaptcha() {

    // 发送异步请求获取验证码图片
    var captchaImage = document.getElementById('captchaImage');
    // 生成随机数作为参数
    var randomNum = Math.floor(Math.random() * 1000);
    captchaImage.src = '/TLProject/captcha/generate?rnd=' + randomNum;
}



/*
window.onload = function() {
    refreshCaptcha()
}*/
