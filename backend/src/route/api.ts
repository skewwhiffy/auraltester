import express from 'express';
import info from './api/info';
import clef from "./api/clef";

const router = express.Router();

router.use('/info', info);
router.use('/clef', clef);

export default router;