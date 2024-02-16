import express from 'express';

const router = express.Router();

router.get('/', (req, res) => {
    res.send({
        version: "ts-0.0.1"
    })
})

export default router;
