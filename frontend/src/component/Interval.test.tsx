import Interval from './Interval'
import { render, screen, queryByAttribute, fireEvent, getByLabelText } from '@testing-library/react'
import axios, { AxiosRequestConfig } from 'axios'
import { act } from 'react-dom/test-utils'

jest.mock('axios', () => ({}))
jest.mock('react-abc', () => ({
  Notation: (props: any) => (
    <div id='notation-props'>{props.notation}</div>
  )
}))

describe.only('Interval', () => {
  let mockAxios: any

  beforeEach(() => {
    mockAxios = axios
    render(<Interval />)
  })

  it('triggers call to API', async () => {
    const mockAbc = 'hello world'
    const urls: Array<string> = []
    const configs: Array<AxiosRequestConfig<any>> = []
    mockAxios.get = async (url: string, config: AxiosRequestConfig<any>) => {
      urls.push(url)
      configs.push(config)
      return {
        data: {
          abc: mockAbc
        }
      }
    }

    await act(async () => {
      ['Alto Clef', 'D', 'b', 'minor', '3']
        .map(it => screen.getByLabelText(it))
        .forEach(it => it.click())
    })

    expect(urls).toHaveLength(1)
    expect(configs).toHaveLength(1)
    expect(urls[0]).toBe('api/interval')
    const params = configs[0].params
    expect(params.clef).toBe('alto')
    const notationElement = screen.getByText(mockAbc)
    expect(notationElement).not.toBeNull()
  })
})