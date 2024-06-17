// script.js
//注册逻辑
document.addEventListener('DOMContentLoaded', function() {
    const registerLink = document.getElementById('register-link');
    const floatingForm = document.getElementById('floating-form-re');
    const closeFloatingForm = document.getElementById('close-floating-form');

    registerLink.addEventListener('click', function(event) {
        event.preventDefault(); // 阻止默认链接行为
        floatingForm.style.display = 'flex'; // 显示浮窗表单
    });

    closeFloatingForm.addEventListener('click', function() {
        floatingForm.style.display = 'none'; // 隐藏浮窗表单
    })
});

document.addEventListener('DOMContentLoaded',function (){
    const errorMsg = document.getElementById('error-message');
    const name = document.getElementById('demo-registerName');
    const psw = document.getElementById('demo-registerPsw');
    const psw2 = document.getElementById('demo-registerPsw2');
    const phone = document.getElementById('demo-registerPhone');
    const email = document.getElementById('demo-registerEmail');
    const check1 = document.getElementById('demo-priority-low');
    const check2 = document.getElementById('demo-priority-normal');
    const check3 = document.getElementById('demo-priority-high');
    const registerbutton = document.getElementById('register-button');

    name.addEventListener('click',function (){
        validateInputs();
        if (comparePSW()){
            pswSolid();
        }
    })

    psw.addEventListener('click',function (){
        validateInputs();
        if (comparePSW()){
        pswSolid();
        }
    })
    psw2.addEventListener('click',function (){
        validateInputs();
        if (comparePSW()){
            pswSolid();
        }
    })
    phone.addEventListener('click',function (){
        validateInputs();
        if (comparePSW()){
            pswSolid();
        }
    })
    email.addEventListener('click',function (){
        validateInputs();
        if (comparePSW()){
            pswSolid();
        }
    })

    check1.addEventListener('click',function (){
        validateInputs();
        if (comparePSW()){
            pswSolid();
        }
    })
    check2.addEventListener('click',function (){
        validateInputs();
        if (comparePSW()){
            pswSolid();
        }
    })
    check3.addEventListener('click',function (){
        validateInputs();
        if (comparePSW()){
            pswSolid();
        }
    })

    registerbutton.addEventListener('click',function (event){
        if (!validateInputs()||!comparePSW()||!pswSolid()){
            event.preventDefault();
            errorMsg.style.display = 'flex';
            errorMsg.textContent='请检查您的表单信息'
        }
    })

    function validateInputs(){
        if(name.value.trim() == ''||psw.value.trim() == ''||psw2.value.trim() == ''||phone.value.trim() == ''||email.value.trim() == ''){
            showError();
            return false;
        }
        else{
            missError()
            return true;
        }
    }

    function comparePSW(){
        if(psw.value!=psw2.value){
            errorMsg.style.display = 'flex';
            errorMsg.textContent='密码不相同'
            return false;
        }
        missError()
        return true;
    }

    function pswSolid(){
        // 使用正则表达式判断是否同时包含中英文字符
        const englishRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/; // 匹配英文字符的正则表达式

        if (!englishRegex.test(psw.value)||psw.length <6) {
            errorMsg.style.display = 'flex';
            errorMsg.textContent='请设置更安全的密码'
            return false;
        }
        missError();
        return true
    }

    function showError(){
        errorMsg.style.display = 'flex';
        errorMsg.textContent='所有字段均为必填选项'
    }
    function missError(){
        errorMsg.style.display = 'none';
    }
})


document.addEventListener('DOMContentLoaded',function (){
    const name = document.getElementById('demo-loginAccount');
    const psw = document.getElementById('demo-psw');
    const login_button = document.getElementById('login_button');

    function validateInputs(event){
        if(name.value.trim() == ''||psw.value.trim() == ''){
            alert("请输入账号密码");
            event.preventDefault();
        }
        else{
            return true;
        }
    }

    login_button.addEventListener("click", validateInputs)
})

