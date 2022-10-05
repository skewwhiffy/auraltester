import { useEffect, useState } from 'react'

type Information = {
  version: string | null
}

const initialInformation: Information = {
  version: null
}


function Info() {
  const [information, setInformation] = useState(initialInformation)

  async function getInformation() {
    const newInformationResponse = await fetch('/info/')
    const newInformation = await newInformationResponse.json()
    console.log(newInformation)
    setInformation(it => newInformation)
  }
  
  useEffect(() => {
    getInformation()
  }, [])
  // getInformation()

  return (
    <div>
      <h1>Information</h1>
      <p>{information.version == null ? 'No version information yet' : `Version: ${information.version}`}</p>
    </div>
  )
}

export default Info