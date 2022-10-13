import {Router} from 'express'
import infoRoutes from './infoRoutes'

const router = Router()

router.use('/info', infoRoutes)

export default router