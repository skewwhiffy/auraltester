import express, { Express, Request, Response } from 'express'
import dotenv from 'dotenv'
import routes from './routes'

dotenv.config()

const app: Express = express()
const port = process.env.PORT
console.log(port)

app.use('/', routes)

app.listen(port, () => {
  console.log(`Server is running at https://localhost:${port}`)
})
