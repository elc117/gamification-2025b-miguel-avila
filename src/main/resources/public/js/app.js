// ===============================
// CARREGA DADOS DO USUÁRIO (da API real)
// ===============================
async function loadUserInfoIntoHeader() {
    const userId = localStorage.getItem("userid");
    if (!userId) return console.error("Usuário não logado");

    try {
        const response = await fetch(`/api/user/info?userid=` + userId);
        const user = await response.json();

        document.getElementById("user-name").textContent = user.name;
        document.getElementById("user-handle").textContent = "@" + user.username;
        // document.getElementById("user-followers").textContent = user.followers;
        // document.getElementById("user-following").textContent = user.following;
        // document.getElementById("user-streak").textContent = "🔥" + user.streak;
        document.getElementById("user-coins").textContent = user.points + "🪙";
        //  document.getElementById("user-avatar").src = user.avatar;

    } catch (err) { console.error("Erro ao carregar infos do usuário:", err); }
}

// ===============================
// INICIALIZA HOME
// ===============================
function initHomeJS() {

  // Botão para abrir/fechar o painel de nova review
  const btn = document.getElementById("new-review-btn");
  const panel = document.getElementById("new-review");

  if (btn && panel) {
    btn.onclick = () => {
      panel.style.display = panel.style.display === "block" ? "none" : "block";
    };
  }

  // Busca dinâmica de filmes
  const titleInput = document.getElementById("movie-title");
  if (titleInput) {
    titleInput.addEventListener("input", searchMovies);
  }
}


// ===============================
// CARREGA REVIEWS DO USUÁRIO
// ===============================
async function loadUserTopReviews() {
    const userId = localStorage.getItem("userid");
    if (!userId) return;

    try {
        const response = await fetch(`/api/user-reviews?userid=` + userId);
        const reviews = await response.json();

        const container = document.getElementById("user-reviews-feed");
        container.innerHTML = "";

        reviews.forEach(r => {
            const card = document.createElement("article");
            card.classList.add("card");

            card.innerHTML = `
                <div class="card-header">
                  <div class="card-thumb">Img</div>
                  <div>
                    <div class="title">${r.movie}</div>
                    <div class="sub">por @${r.user} • ⭐${r.rating}</div>
                    <p class="card-text">${r.comment}</p>
                  </div>
                </div>
            `;

            container.appendChild(card);
        });

    } catch (err) {
        console.error("Erro ao carregar reviews:", err);
    }
}

async function loadItensDaLoja() {
    try {
        const response = await fetch("/api/store/items");
        const itens = await response.json();

        const container = document.getElementById("loja-itens-feed");
        container.innerHTML = "";

        itens.forEach(item => {
            const card = document.createElement("article");
            card.classList.add("card", "shop-item");

            card.innerHTML = `
                <div class="card-header">
                  <div class="card-thumb">💸</div>
                  <div>
                    <div class="title">${item.name}</div>
                    <p class="card-text">${item.description}</p>
                    <div class="shop-price">${item.price}🪙</div>
                    <button class="btn buy-btn">Comprar</button>
                  </div>
                </div>
            `;

            const btn = card.querySelector(".buy-btn");

            btn.addEventListener("click", async () => {
                const userId = localStorage.getItem("userid");

                if (!userId) { return alert("Você precisa estar logado para comprar."); }

                try {
                    const resp = await fetch(`/api/store/buy?userid=${userId}&itemid=${item.id}`, {
                        method: "POST"
                    });

                    const result = await resp.json();

                    if (result.status === "PURCHASE_OK")            { alert("Compra realizada com sucesso!"); } 
                    else if (result.status === "NOT_ENOUGH_POINTS") { alert("Pontos insuficientes!"); }
                    else if (result.status === "ITEM_NOT_FOUND")    { alert("Item não encontrado!"); }
                    loadUserInfoIntoHeader();
                } catch (err) {
                    console.error("Erro ao comprar item:", err);
                    alert("Erro ao comunicar com o servidor.");
                }
            });

            container.appendChild(card);
        });

    } catch (err) {
        console.error("Erro ao carregar itens da loja:", err);
    }
}


function injectUserHeader() {
    const container = document.querySelector(".dynamic-user-header");
    container.innerHTML = "";

    const template = document.getElementById("user-header");
    container.appendChild(template.content.cloneNode(true));

    loadUserInfoIntoHeader();
}



// ===============================
// INICIALIZA PERFIL
// ===============================
function initPerfilJS() {
    injectUserHeader();
    loadUserTopReviews();
}


// ===============================
// INICIALIZA LOJA
// ===============================
function initLojaJS() {
    injectUserHeader();
    loadItensDaLoja();
}



// ===============================
// NAVEGAÇÃO ENTRE PÁGINAS
// ===============================
const mainPanels = document.querySelector(".main-panels");

function loadPage(id) {
    const template = document.getElementById(id);
    mainPanels.innerHTML = "";
    mainPanels.appendChild(template.content.cloneNode(true));

    document.querySelectorAll(".nav a").forEach(a => a.classList.remove("active"));
    document.getElementById("nav-" + id.replace("page-", "")).classList.add("active");

    if (id === "page-home") {
        initHomeJS();
        loadReviews();
    }
    if (id === "page-perfil") initPerfilJS();
    if (id === "page-loja")   initLojaJS();
}

document.getElementById("nav-home").onclick   = () => loadPage("page-home");
document.getElementById("nav-perfil").onclick = () => loadPage("page-perfil");
document.getElementById("nav-loja").onclick   = () => loadPage("page-loja");

loadPage("page-home");