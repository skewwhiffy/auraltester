import {Notation} from 'react-abc'
import {useState} from "react";
import {useQuery} from "@tanstack/react-query";
import api from "../../api/api";
import H1 from "../../component/H1";
import P from "../../component/P";
import SpinUntilReady from "../../component/SpinUntilReady";
import ClefSelector from "../../component-old/selector/ClefSelector";
import Centre from "../../component/Center";

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
      <Centre>
        {/* Replace with a shinier version */}
        <ClefSelector value={clef} onChange={setClef} />
      </Centre>
    </>
  )
}
