import { Notation } from 'react-abc'
import { useState } from "react"
import { Clef, Interval, Note } from "../../common/types"
import Centre from "../../component/Centre"
import api from "../../api/api"
import { useQuery } from "@tanstack/react-query"
import SpinUntilReady from "../../component/SpinUntilReady"
import ClefSelector from '../../component/selector/ClefSelector'
import NoteSelector from '../../component/selector/NoteSelector'
import IntervalSelector from '../../component/selector/IntervalSelector'

const IntervalExample = () => {
  const [clef, setClef] = useState<Clef | undefined>()
  const [interval, setInterval] = useState<Interval | undefined>()
  const [bottomNote, setBottomNote] = useState<Note | undefined>()
  const getIntervalAbc = useQuery({
    queryKey: ['interval', clef, interval],
    queryFn: async () => {
      return await api.getInterval({
        clef: clef!,
        bottomNote: '',
        interval: interval!,
        keySignature: ''
      })
    },
    enabled: !!clef && !!interval
  })


  return (
    <>
      <Centre>
        <SpinUntilReady isLoading={getIntervalAbc.isLoading}>
          {
            getIntervalAbc.data?.data.abc
            && <Notation notation={getIntervalAbc.data.data.abc} />
          }
        </SpinUntilReady>
      </Centre>
      <ClefSelector
        title="Select your clef"
        value={clef}
        onChange={setClef}
      />
      <NoteSelector
        title="Select your bottom note"
        value={bottomNote}
        onChange={setBottomNote}
      />
      <IntervalSelector
        title="Select your interval"
        value={interval}
        onChange={setInterval}
      />
    </>
  )
}

export default IntervalExample

