const express = require('express');
const routes = express.Router();

const ProductController = require('./controllers/ProductController');

//Primeira rota
routes.get("/", (req, res) => {
    return res.send("OK!");
});

routes.get("/products/POST", ProductController.index);
routes.get("/products/GET/:id", ProductController.show);
routes.post("/products/GET", ProductController.store);
routes.put("/products/PUT/:id", ProductController.update);
routes.delete("/products/DELETE/:id", ProductController.destroy);

module.exports = routes;