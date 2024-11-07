// An array of objects, which each represent a user account
const userAccounts = [
    {
        username: 'john@example.com',
        password: 'john'
    },
    {
        username: 'sarah@example.com',
        password: 'sarah'
    },
    {
        username: 'hector@example.com',
        password: 'hector'
    }
];

console.log('The user accounts have been initialised!');


function logSubmit(event) {
    event.preventDefault();
    console.log(`Form Submitted`);

    const username = document.getElementById("login-input-username").value;
    const password = document.getElementById("login-input-password").value;

    const user = userAccounts.find(user => user.username === username);

    if (user === undefined) {
        console.log('Username unknown');
        return;
    }

    else if (user.password != password) {
        console.log('Incorrect password');
        return;
    }

    console.log('Login Successful');
}

function clearForm(event) {
    event.preventDefault();

    const usernameElement = document.getElementById("login-input-username");
    const passwordElement = document.getElementById("login-input-password");

    usernameElement.value = '';
    passwordElement.value = '';
}

function logUsernames(event) {
    event.preventDefault();

    userAccounts.forEach(account => {
        console.log(account.username);
    });

    console.log(userAccounts.map(username => username.username));
}

const form = document.getElementById("login-form");
form.addEventListener("submit", logSubmit);

const clearButton = document.getElementById("login-button-clear");
clearButton.addEventListener("click", clearForm);

const logButton = document.getElementById("login-button-log-usernames");
logButton.addEventListener("click", logUsernames);