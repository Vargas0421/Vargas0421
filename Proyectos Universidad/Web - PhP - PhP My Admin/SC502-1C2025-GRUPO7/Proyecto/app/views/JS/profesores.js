// Lista de ejemplo de profesores (puedes obtenerla por AJAX o PHP también)
const profesores = [
    {
      nombre: "Ana",
      apellido: "Ramírez",
      especialidad: "Matemáticas",
      imagen: "views/Images/prof1.jpg"
    },
    {
      nombre: "Carlos",
      apellido: "Mendoza",
      especialidad: "Ciencias",
      imagen: "views/Images/prof2.jpg"
    },
    {
      nombre: "Lucía",
      apellido: "Fernández",
      especialidad: "Literatura",
      imagen: "views/Images/prof3.jpg"
    }
  ];
  
  // Generar dinámicamente las tarjetas
  const container = document.getElementById('profesores-container');
  
  profesores.forEach(profesor => {
    const col = document.createElement('div');
    col.className = 'col-md-4 d-flex';
  
    const card = `
      <div class="card w-100">
        <img src="${profesor.imagen}" class="card-img-top" alt="${profesor.nombre}">
        <div class="card-body d-flex flex-column">
          <h5 class="card-title">${profesor.nombre} ${profesor.apellido}</h5>
          <p class="card-text">Especialidad: ${profesor.especialidad}</p>
          <a href="#" class="btn btn-outline-primary mt-auto">Ver perfil</a>
        </div>
      </div>
    `;
  
    col.innerHTML = card;
    container.appendChild(col);
  });
  