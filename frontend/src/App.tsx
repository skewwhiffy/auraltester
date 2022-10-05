import Info from './component/Info'
import { Notation } from "react-abc";

const notation = 'CDEF GABc|';

function App() {
  return (
    <div className="App">
      <Info />
      <Notation notation={notation} />
    </div>
  )
}

export default App
