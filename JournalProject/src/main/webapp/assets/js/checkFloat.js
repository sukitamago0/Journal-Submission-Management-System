// script.js
//注册逻辑
document.addEventListener('DOMContentLoaded', function() {
    const registerLink = document.getElementById('check_journal');
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

