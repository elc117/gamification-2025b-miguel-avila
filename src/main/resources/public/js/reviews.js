async function loadReviews() {
  try {
    const res = await fetch('/api/reviews'); 
    const reviews = await res.json();

    const feed = document.getElementById('reviews-feed');
    feed.innerHTML = '';

    reviews.forEach(review => {
      const stars = '⭐'.repeat(review.rating);
      const card = document.createElement('article');
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
