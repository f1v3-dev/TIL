const SERVER_URL = "http://133.186.241.167:8100";

window.addEventListener("DOMContentLoaded", function () {
    "use strict";

    const validateForm = function (form) {
        const id = form["userId"];
        const password = form["userPassword"];

        if (id.value.trim() == "") {
            alert("아이디를 입력해주세요.");
            id.focus();
            return false;
        } else if (password.value == "") {
            alert("패스워드를 입력해주세요");
            password.focus();
            return false;
        }

        return true;
    };

    const loginForm = document.getElementById("login-form");

    loginForm.addEventListener("submit", async function (event) {
        event.preventDefault();
        if (!validateForm(event.target)) {
            return;
        }

        const userId = event.target["userId"].value;
        const userPassword = event.target["userPassword"].value;

        let user;

        try {
            user = await doLogin(userId, userPassword);

            // UI 처리
            const loginWrapper = document.getElementById("login-wrapper");
            loginWrapper.setAttribute("style", "display: none;");

            const success = document.getElementById("login-success");
            success.setAttribute("style", "display: block;");

            const loginUserId = document.getElementById("login-userId");
            loginUserId.innerText = user.userId;

            const loginUserName = document.getElementById("login-userName");
            loginUserName.innerText = user.userName;

            const loginCartId = document.getElementById("login-cartId");
            loginCartId.innerText = user.cartId;

        } catch (e) {
            console.log(e);
            return;
        }

        let items;
        try {
            items = await getCartItems(userId, user.cartId);
            // UI 처리
            const cartTable = document.getElementById("cart-table");
            const body = cartTable.getElementsByTagName("tbody")[0];
            const intl = new Intl.NumberFormat();

            items.forEach((item) => {
                console.log(item);
            });

            items.forEach((item) => {
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
        } catch (e) {
            console.log(e);
        }
    });
});

async function doLogin(paramUserId, paramuserPassword) {
    const url = SERVER_URL + "/api/users/login";
    const data = {
        userId: paramUserId,
        userPassword: paramuserPassword
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(data)
    }

    const response = await fetch(url, options);

    if (!response.ok) {
        throw new Error("login error!");
    }
    return response.json();
}

async function getCartItems(userId, cartId) {
    const url = SERVER_URL + "/api/nhnmart/shopping-cart/" + cartId;

    console.log(url);

    const options = {
        method: 'GET', headers: {
            'Content-Type': 'application/json', 'X-USER-ID': userId
        }
    }

    const response = await fetch(url, options);

    if (!response.ok) {
        throw new Error("get cart items error!");
    }

    return response.json();
}