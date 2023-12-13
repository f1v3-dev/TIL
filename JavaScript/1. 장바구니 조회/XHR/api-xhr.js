const SERVER_URL = "http://133.186.241.167:8100"

window.addEventListener("DOMContentLoaded", function() {
    'use strict';
    const loginForm = this.document.getElementById("login-form");

    const validateForm = function(form) {
        const id = form['userId'];
        const password = form['userPassword'];

        if (id.value.trim() == '') {
            alert('아이디를 입력해주세요.');
            id.focus();
            return false;
        } else if (password.value == '') {
            alert('패스워드를 입력해주세요');
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

        const loginSuccess = function(user) {
            console.log("userId = " + user.userId);
            console.log("cartId = " + user.cartId);

            // loginUI 구현
            const loginWrapper = document.getElementById("login-wrapper");
            loginWrapper.setAttribute('style', 'display: none;');

            const success = document.getElementById("login-success");
            success.setAttribute('style', 'display: block;');

            const loginUserId = document.getElementById("login-userId");
            loginUserId.innerText = user.userId;

            const loginUserName = document.getElementById("login-userName");
            loginUserName.innerText = user.userName;

            const loginCartId = document.getElementById("login-cartId");
            loginCartId.innerText = user.cartId;
            

            // cart 구현
            getCartItems(user.userId, user.cartId, function(items) {
                const cartTable = document.getElementById("cart-table");
                const body = cartTable.getElementsByTagName("tbody")[0];
                const intl = new Intl.NumberFormat();

                items.forEach(item => {
                    const tr = document.createElement("tr");
                    const td1 = document.createElement("td");
                    const td2 = document.createElement("td");
                    const td3 = document.createElement("td");
                    const td4 = document.createElement("td");
                    const td5 = document.createElement("td");

                    td1.innerText = item.productId;
                    td2.innerText = item.name;
                    td3.innerText = intl.format(item.price);
                    td4.innerText = intl.format(item.amount);
                    td5.innerText = intl.format(item.totalPrice);

                    tr.append(td1, td2, td3, td4, td5);

                    body.append(tr);
                });
            });
        }

        // 로그인 처리
        doLogin(loginForm['userId'].value, loginForm['userPassword'].value, loginSuccess);
    });
});

function doLogin(paramUserId, paramuserPassword, loginSuccess) {
    console.log("call doLogin");

    const xhr = new XMLHttpRequest();
    const url = SERVER_URL + "/api/users/login";
    console.log(url);

    const data = {
        userId : paramUserId,
        userPassword : paramuserPassword
    }

    xhr.addEventListener('load', function() {
        if(this.status === 200) {
            console.log(this.response);
            loginSuccess(this.response);
        }
    });

    xhr.open("POST", url);
    xhr.setRequestHeader("content-type", "application/json");
    xhr.responseType="json";
    xhr.send(JSON.stringify(data));
}

function getCartItems(userId, cartId, displayCartItems) {

    const xhr = new XMLHttpRequest();
    const url = SERVER_URL + "/api/nhnmart/shopping-cart/" + cartId;
    console.log(url);
    
    xhr.addEventListener('load', function() {
        if (this.status === 200) {
            console.log(this.response);
            displayCartItems(this.response);
        }
    });

    xhr.addEventListener("error", function() {
        throw new Error();
    });

    xhr.open("GET", url);
    xhr.setRequestHeader("content-type", "application/json");
    xhr.setRequestHeader("X-USER-ID", userId);
    xhr.responseType="json";
    xhr.send('');
}