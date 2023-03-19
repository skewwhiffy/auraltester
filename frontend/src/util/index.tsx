import api from './api'

const capitalizeFirstCharacter = (source: string): string => source.length === 0
  ? ''
  : `${source[0].toUpperCase()}${source.substring(1)}`

export { capitalizeFirstCharacter, api }
