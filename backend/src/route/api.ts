import express from 'express';
import info from './api/info';

const router = express.Router();

router.use('/info', info);

export default router;