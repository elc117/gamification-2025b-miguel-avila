// procura no banco os filmes sendo pesquisados
async function searchMovies() {
  const titleInput = document.getElementById("movie-title");
  if (!titleInput) return;

  const query = titleInput.value.trim();
  const suggestionsBox = document.getElementById("movie-suggestions");
  const movieList = document.getElementById("movie-list");
  const addBtn = document.getElementById("add-new-movie");

  if (!suggestionsBox || !movieList || !addBtn) return;

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
  } catch (e) { console.error("Erro:", e); }
}

// seleciona o filme a ser tratado
function selectMovie(movie) {
  const titleInput = document.getElementById("movie-title");
  if (titleInput) titleInput.value = movie.name;
  document.getElementById("movie-suggestions").style.display = "none";
}

// envia um novo filme
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

  } catch (e) { console.error("Erro ao enviar filme:", e); }
}

