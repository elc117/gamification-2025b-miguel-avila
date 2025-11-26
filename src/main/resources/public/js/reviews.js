// mostra apenas os primeiros n caracteres de uma string
const limitString = (string, n) => { return string.substring(0, n) + (string.length > n ? '...' : ''); };

// carrega as 3 reviews mais novas para o html
async function loadReviews() {
  try {
    const res = await fetch('/api/reviews'); 
    const reviews = await res.json();

    const feed = document.getElementById('reviews-feed');
    feed.innerHTML = '';

    reviews.forEach(review => {
      const stars = '⭐'.repeat(review.rating);
      const card = document.createElement('article');
      review.comment = limitString(review.comment, 50);
      card.classList.add('card');
      card.innerHTML = `
        <div class="card-header">
          <div class="card-thumb">Img</div>
          <div>
            <div class="title">${review.movie}</div>
            <div class="sub">por @${review.user} • ${stars}</div>
            <p class="card-text">${review.comment}</p>
          </div>
        </div>
      `;
      feed.appendChild(card);
    });


  } catch (err) { console.error('Erro ao carregar reviews:', err); }
}

loadReviews();

// envia uma nova review
async function submitReview() {
  // pega o filme selecionado
  const titleInput = document.getElementById("movie-title");
  if (!titleInput || !titleInput.value) {
    return alert("Selecione um filme!");
  }

  // aqui você precisa do ID do filme; supondo que você tenha salvo na seleção
  const movieId = window.selectedMovieId;
  if (!movieId) { return alert("Filme inválido!"); }

  // pega o rating selecionado
  const ratingInputs = document.getElementsByName("rating");
  let rating = null;
  ratingInputs.forEach(input => { if (input.checked) rating = parseInt(input.value); });
  if (!rating) { return alert("Selecione uma avaliação!"); }

  // pega o comentário
  const commentInput = document.getElementById("review-comment");
  const comment = commentInput ? commentInput.value.trim() : "";

  // pega o usuário logado
  const userId = localStorage.getItem("userid");
  if (!userId) { return alert("Usuário não está logado!"); }

  const review = { userId, movieId, rating, comment };

  try {
    const resp = await fetch("/api/reviews", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(review)
    });

    if (!resp.ok) {
      return alert("Erro ao enviar review!");
    }

    const newReview = await resp.json();
    alert("Review enviada com sucesso!");
    console.log("Nova review:", newReview);

    // opcional: resetar o formulário
    if (commentInput) commentInput.value = "";
    ratingInputs.forEach(input => (input.checked = false));
    titleInput.value = "";
    window.selectedMovieId = null;

  } catch (e) {
    console.error("Erro ao enviar review:", e);
    alert("Erro de comunicação com o servidor.");
  }
  loadReviews();
}

async function loadUserTopReviews() {
    const userId = localStorage.getItem("userid"); // você já salva isso no login

    if (!userId) {
        console.error("Usuário não logado!");
        return;
    }

    try {
        const response = await fetch(`/api/reviews/top?userid=${userId}`);
        const reviews = await response.json();

        const container = document.getElementById("user-reviews-feed");
        container.innerHTML = ""; // limpa antes
        
        reviews.forEach(r => {
          const div = document.createElement("div");
          r.comment = limitString(r.comment, 50);
          div.classList.add("review-card");
          
            div.innerHTML = `
                <article class="card">
                <div class="card-header">
                  <div class="card-thumb">Img</div>
                  <div>
                    <div class="title">${r.movie}</div>
                    <div class="sub">por @${r.user} • ${r.rating} ⭐</div>
                    <p class="card-text">${r.comment}</p>
                  </div>
                </div>
              </article>
              `;

            container.appendChild(div);
        });

    } catch (err) {
        console.error("Erro ao carregar reviews:", err);
    }
}

async function loadUserInfo() {
    const userId = localStorage.getItem("userid");
    if (!userId) return;

    try {
        const response = await fetch(`/api/user/info?userid=${userId}`);
        const user = await response.json();

        document.getElementById("profile-name").textContent = user.name;
        document.getElementById("profile-handle").textContent = "@" + user.username;
        document.getElementById("profile-points").textContent = user.points + "🪙";

    } catch (err) {
        console.error("Erro ao carregar infos do usuário:", err);
    }
}
