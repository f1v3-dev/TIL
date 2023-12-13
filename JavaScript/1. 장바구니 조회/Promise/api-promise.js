const SERVER_URL = "http://133.186.241.167:8100";

window.addEventListener("DOMContentLoaded", function() {
    'use strict';

    const loginForm = this.document.getElementById("login-form");

    const validateForm = function(form) {
        const id = form["user_id"];
        const password = form["user_password"];

        if (id.value.trim() == '') {
            alert("아이디를 입력해주세요.");
            id.focus();
            return false;
        } else if (password.value.trim() == '') {
            alert("비밀번호를 입력해주세요.");
            password.focus();
            return false;
        }

        return true;
    };
    

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();
        if (!validateForm(event.target)) {
            return;
        }
    });

});