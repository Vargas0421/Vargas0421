<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acceso Denegado</title>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background: white;
            padding: 40px 60px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }
        h1 {
            font-size: 48px;
            color: #dc3545;
            margin-bottom: 10px;
        }
        p {
            font-size: 18px;
            color: #333;
            margin-bottom: 30px;
        }
        .btn {
            display: inline-block;
            padding: 12px 24px;
            margin: 5px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            transition: background-color 0.3s ease;
            border: none;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .emoji {
            font-size: 50px;
            margin-bottom: 10px;         
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="emoji">🔒</div>
        <h1>Acceso Denegado</h1>
        <p>No tenés permisos para acceder a esta página.</p>
        <button class="btn" onclick="history.back()">🔙 Volver atrás</button>
        <a class="btn" href="../index.php?action=logout">🏠 Ir al log in</a>
    </div>
</body>
</html>
