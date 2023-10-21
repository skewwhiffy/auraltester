import { useState } from "react"
import { Interval } from "../../common/types"

const IntervalExample = () => {
  const [clef, setClef] = useState<string | undefined>()
  const [interval, setInterval] = useState<Interval | undefined>()

  return (
    <p>HELLO</p>
  )
}

export default IntervalExample

