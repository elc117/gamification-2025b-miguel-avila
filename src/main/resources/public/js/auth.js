/* Alterna entre formulários */
function goToRegister() {
  document.getElementById('login-form').classList.remove('active');
  document.getElementById('signup-form').classList.add('active');
}

function goToLogin() {
  document.getElementById('signup-form').classList.remove('active');
  document.getElementById('login-form').classList.add('active');
}

/* Função para login */
async function doLogin() {
  const email = document.getElementById('login-email').value.trim();
  const password = document.getElementById('login-password').value.trim();

  if (!email || !password) return alert("Preencha todos os campos!");

  try {
    const resp = await fetch('/api/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });

    if (!resp.ok) return alert("Email ou senha inválidos!");

    const user = await resp.json();

    localStorage.setItem("userid", user.id);
    localStorage.setItem("username", user.username);
    localStorage.setItem("name", user.name);


    alert("Login realizado com sucesso!");
    // aqui você pode redirecionar ou mostrar painel de usuário
    console.log("Usuário logado:", user);

  } catch (e) {
    console.error(e);
    alert("Erro de comunicação com o servidor.");
  }
  window.location.href = "/app.html";
}

/* Função para registro */
async function doRegister() {
  const name = document.getElementById('reg-name').value.trim();
  const username = document.getElementById('reg-username').value.trim();
  const email = document.getElementById('reg-email').value.trim();
  const password = document.getElementById('reg-pass').value.trim();
  const password2 = document.getElementById('reg-pass2').value.trim();

  if (!name || !username || !email || !password || !password2) return alert("Preencha todos os campos!");

  if (password !== password2) return alert("As senhas não coincidem!");

  const userObj = { name, username, email, password };

  try {
    // verifica se usuário/email já existe
    const verifyResp = await fetch('/api/users/exist', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userObj)
    });

    const exists = await verifyResp.json();
    if (exists) return alert("Usuário ou email já cadastrados!");

    // cria usuário
    const createResp = await fetch('/api/users', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userObj)
    });

    if (!createResp.ok) return alert("Erro ao criar usuário!");

    const newUser = await createResp.json();
    localStorage.setItem("userid", newUser.id);
    localStorage.setItem("username", newUser.username);
    localStorage.setItem("name", newUser.name);


    alert("Conta criada com sucesso!");
    goToLogin(); // volta para login ou já loga automaticamente

  } catch (e) {
    console.error(e);
    alert("Erro de comunicação com o servidor.");
  }
}