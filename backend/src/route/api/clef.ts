import express from 'express';
import { getClef } from '../../service/clefService'
import {ClefType} from "../../model/clef";

const router = express.Router();

router.get('/', (req, res) => {
    console.log(req.query.clef)
    const response = getClef(req.query.clef as ClefType);
    res.send(response);
})

export default router;