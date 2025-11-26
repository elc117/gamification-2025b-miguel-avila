const mainPanels = document.querySelector(".main-panels");

function loadPage(id) {
  const template = document.getElementById(id);
  mainPanels.innerHTML = "";
  mainPanels.appendChild(template.content.cloneNode(true));

  document.querySelectorAll(".nav a").forEach(a => a.classList.remove("active"));
  document.getElementById("nav-" + id.replace("page-", "")).classList.add("active");

  // roda scripts específicos da página
  if (id === "page-home") initHomeJS();
  if (id === "page-perfil") initPerfilJS();
}

document.getElementById("nav-home").onclick =   () => loadPage("page-home");
document.getElementById("nav-perfil").onclick = () => loadPage("page-perfil");
document.getElementById("nav-loja").onclick = () => loadPage("page-loja");

loadPage("page-home");


// ------------------------------------------------------
//        INICIALIZA A HOME (só quando ela existe!)
// ------------------------------------------------------
function initHomeJS() {
  const btn = document.getElementById("new-review-btn");
  const panel = document.getElementById("new-review");

  if (btn) {
     btn.onclick = () => {
        panel.style.display = panel.style.display === "block" ? "none" : "block";
     };
  }

  // ativa busca dinâmica
  const titleInput = document.getElementById("movie-title");
  if (titleInput) titleInput.addEventListener("input", searchMovies);
}

// Perfil (se quiser adicionar coisas)
function initPerfilJS() {}


// ------------------------------------------------------
//   BUSCA DINÂMICA — agora sem quebrar sua aplicação
// ------------------------------------------------------
async function searchMovies() {
  const titleInput = document.getElementById("movie-title");
  if (!titleInput) return; // evita erro quando não está na home

  const query = titleInput.value.trim();
  const suggestionsBox = document.getElementById("movie-suggestions");
  const movieList = document.getElementById("movie-list");
  const addBtn = document.getElementById("add-new-movie");

  if (!suggestionsBox || !movieList || !addBtn) return; // segurança extra

  if (query === "") {
    suggestionsBox.style.display = "none";
    return;
  }

  try {
    const resp = await fetch(`/api/movies/search?title=${encodeURIComponent(query)}`);
    const movies = await resp.json();

    movieList.innerHTML = "";

    if (movies.length > 0) {
      addBtn.style.display = "none";
      movies.forEach(movie => {
        const li = document.createElement("li");
        li.textContent = movie.name;
        li.onclick = () => selectMovie(movie);
        movieList.appendChild(li);
      });
    } else {
      movieList.innerHTML = "<li>Nenhum filme encontrado</li>";
      addBtn.style.display = "block";
    }

    suggestionsBox.style.display = "block";
  } catch (e) {
    console.error("Erro:", e);
  }
}

function selectMovie(movie) {
  const titleInput = document.getElementById("movie-title");
  if (titleInput) titleInput.value = movie.name;
  document.getElementById("movie-suggestions").style.display = "none";
}


// ------------------------------------------------------
//    FORM DE NOVO FILME
// ------------------------------------------------------
function showAddMovieForm() {
  document.getElementById("new-movie-form").style.display = "block";
  document.getElementById("add-new-movie").style.display = "none";
}

function cancelAddMovie() {
  document.getElementById("new-movie-form").style.display = "none";
}

async function submitNewMovie() {
  const title = document.getElementById("movie-title").value.trim();
  const director = document.getElementById("new-movie-director").value.trim();
  const mainActor = document.getElementById("new-movie-actor").value.trim();
  const genre = document.getElementById("new-movie-genre").value.trim();
  const year = document.getElementById("new-movie-year").value.trim();

  if (!title || !director || !mainActor || !genre || !year) {
    alert("Preencha todos os campos!");
    return;
  }

  const newMovie = { title, director, mainActor, genre, year };

  try {
    const resp = await fetch("/api/movies", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newMovie)
    });

    if (!resp.ok) return alert("Erro ao adicionar filme!");

    alert("Filme adicionado!");

    document.getElementById("new-movie-form").style.display = "none";
    document.getElementById("movie-suggestions").style.display = "none";

  } catch (e) {
    console.error("Erro ao enviar filme:", e);
  }
}
