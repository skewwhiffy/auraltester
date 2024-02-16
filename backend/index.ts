import express from "express";
import api from "./src/route/api";

const app = express();
const port = 3001;

app.use('/api', api)
app.get('/', (req, res) => {
    res.send('HELLO WORLD');
})

app.listen(port, () => {
    console.log(`[server]: Server is running on port ${port}`)
})