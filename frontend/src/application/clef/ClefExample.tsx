import {Notation} from 'react-abc'
import {useState} from "react";
import {useQuery} from "@tanstack/react-query";
import api from "../../api/api";
import SpinUntilReady from "../../component/SpinUntilReady";
import Centre from "../../component/Center";
import ClefSelector from "../../component/selector/ClefSelector";

export default ClefExample

function ClefExample() {
  const [clef, setClef] = useState<string | undefined>()
  const getClefAbc = useQuery({
    queryKey: ['clef', clef],
    queryFn: async () => {
      return clef ? api.getClef(clef) : null
    }
  })

  return (
    <>
      <Centre>
        <SpinUntilReady isLoading={getClefAbc.isLoading}>
          {getClefAbc.data?.data.abc && <Notation notation={getClefAbc.data.data.abc}/>}
        </SpinUntilReady>
      </Centre>
      <ClefSelector title="Select your clef" value={clef} onChange={setClef}/>
    </>
  )
}
