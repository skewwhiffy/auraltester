import express from 'express';

const router = express.Router();

router.get('/info', (req, res) => {
    res.send('HELLO INFO')
})

export default router;