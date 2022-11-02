const capitalizeFirstCharacter = (source: string) => {
  return `${source[0].toUpperCase()}${source.substring(1)}`
}

export { capitalizeFirstCharacter }
