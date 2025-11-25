/* Ajustar altura automática */
function adjustHeight() {
  const forms = document.querySelector(".forms");
  const activeForm = document.querySelector(".form.active");
  forms.style.height = activeForm.offsetHeight + "px";
}

/* Trocar para a tela de cadastro */
function goToRegister() {
  document.getElementById("login-form").classList.remove("active");
  document.getElementById("signup-form").classList.add("active");
  adjustHeight();
}

/* Trocar para a tela de login */
function goToLogin() {
  document.getElementById("signup-form").classList.remove("active");
  document.getElementById("login-form").classList.add("active");
  adjustHeight();
}

/* Mostrar / esconder senha */
function togglePass(id) {
  const input = document.getElementById(id);
  input.type = input.type === "password" ? "text" : "password";
}

/* Chama ao carregar a página */
window.onload = adjustHeight;

/* LOGIN */
async function doLogin() {
  const email = document.querySelector("#login-form input[name=email]").value;
  const password = document.querySelector("#login-form input[name=password]").value;

  const res = await fetch("/auth/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  });

  if (res.ok) {
    window.location.href = "app.html";
  } else {
    alert(await res.text());
  }
}

/* REGISTER */
async function doRegister() {
  const name = document.querySelector("#signup-form input[name=name]").value;
  const user = document.querySelector("#signup-form input[name=user]").value;
  const email = document.querySelector("#signup-form input[name=email]").value;

  const pass = document.getElementById("reg-pass").value;
  const pass2 = document.getElementById("reg-pass2").value;

  if (pass !== pass2) {
    return alert("As senhas não coincidem!");
  }

  const res = await fetch("/auth/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, user, email, password: pass })
  });

  if (res.ok) {
    alert("Conta criada! Faça login.");
    goToLogin();
  } else {
    alert(await res.text());
  }
}
