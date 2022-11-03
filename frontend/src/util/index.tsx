const capitalizeFirstCharacter = (source: string) => {
  return source.length == 0
    ? ''
    : `${source[0].toUpperCase()}${source.substring(1)}`
}

export { capitalizeFirstCharacter }
