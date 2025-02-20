const translations = {
    es: {
        home: "Inicio",
        services: "Servicios",
        more: "Más",
        optimization: "Optimización",
        technology: "Tecnología",
        contact: "Contacto",
        search: "Buscar",
        language: "Idioma",
        subtitle: "Impulsa tu Negocio con Nuestra Plataforma Innovadora",
        description: "Descubre cómo nuestra tecnología puede transformar tus operaciones y",
        benefits: "Beneficios Principales",
        efficiency: "Incremento de Eficiencia",
        "efficiency-description": "Optimiza tus procesos y aumenta la productividad.",
        "technology-description": "Utiliza las últimas herramientas para destacar en el mercado.",
        support: "Soporte Continuo",
        "support-description": "Asistencia técnica y soporte cuando lo necesites."
    },
    en: {
        home: "Home",
        services: "Services",
        more: "More",
        optimization: "Optimization",
        technology: "Technology",
        contact: "Contact",
        search: "Search",
        language: "Language",
        subtitle: "Boost Your Business with Our Innovative Platform",
        description: "Discover how our technology can transform your operations and",
        benefits: "Main Benefits",
        efficiency: "Efficiency Increase",
        "efficiency-description": "Optimize your processes and increase productivity.",
        "technology-description": "Use the latest tools to stand out in the market.",
        support: "Continuous Support",
        "support-description": "Technical assistance and support when you need it."
    }
};

function changeLanguage(lang) {
    const elements = document.querySelectorAll("[data-lang]");
    elements.forEach(element => {
        const key = element.getAttribute("data-lang");
        if (translations[lang] && translations[lang][key]) {
            element.textContent = translations[lang][key];
        }
    });
}

document.addEventListener("DOMContentLoaded", () => {
    const languageLinks = document.querySelectorAll(".dropdown-item[data-lang]");
    languageLinks.forEach(link => {
        link.addEventListener("click", (e) => {
            e.preventDefault();
            const lang = link.getAttribute("data-lang");
            changeLanguage(lang);
        });
    });

    changeLanguage("es");
});