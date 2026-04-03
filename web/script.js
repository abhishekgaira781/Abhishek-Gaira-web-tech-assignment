document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('registrationForm');
    const formMessage = document.getElementById('formMessage');
    const userList = document.getElementById('userList');
    const usersSection = document.getElementById('usersSection');

    let users = JSON.parse(localStorage.getItem('users') || '[]');
    renderUsers();

    form.addEventListener('submit', (event) => {
        event.preventDefault();

        const username = document.getElementById('username').value.trim();
        const email = document.getElementById('email').value.trim();

        if (username === '' || email === '') {
            formMessage.textContent = 'Please fill in all fields.';
            formMessage.className = 'error';
        } else {
            users.push({ username, email });
            localStorage.setItem('users', JSON.stringify(users));
            formMessage.textContent = `Registration successful for ${username}!`;
            formMessage.className = 'success';
            form.reset();
            renderUsers();
        }
    });

    function renderUsers() {
        userList.innerHTML = '';
        if (users.length === 0) {
            usersSection.style.display = 'none';
            return;
        }
        usersSection.style.display = 'block';
        users.forEach(u => {
            const li = document.createElement('li');
            li.textContent = `${u.username} — ${u.email}`;
            userList.appendChild(li);
        });
    }
});
