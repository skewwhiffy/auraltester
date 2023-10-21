import { Notation } from 'react-abc'
import { useState } from "react";
import { useQuery } from "@tanstack/react-query";
import api from "../../api/api";
import SpinUntilReady from "../../component/SpinUntilReady";
import Centre from "../../component/Centre";
import ClefSelector from "../../component/selector/ClefSelector";
import { Clef } from '../../common/types';

const ClefExample = () => {
  const [clef, setClef] = useState<Clef | undefined>()
  const getClefAbc = useQuery({
    queryKey: ['clef', clef],
    queryFn: async () => {
      return await api.getClef(clef!)
    },
    enabled: !!clef
  })

  return (
    <>
      <Centre>
        <SpinUntilReady isLoading={getClefAbc.isLoading}>
          {
            getClefAbc.data?.data.abc
            && <Notation notation={getClefAbc.data.data.abc} />
          }
        </SpinUntilReady>
      </Centre>
      <ClefSelector
        title="Select your clef"
        value={clef}
        onChange={setClef}
      />
    </>
  )
}

export default ClefExample