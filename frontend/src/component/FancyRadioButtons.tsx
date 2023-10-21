import {Children, isValidElement, PropsWithChildren, ReactElement} from "react";

export default FancyRadioButtons

interface FancyRadioButtonsProps extends PropsWithChildren {
  value?: string | null

  onChange(value: string): void
}

interface ItemProps {
  children: string
}

function FancyRadioButtons({children, onChange}: FancyRadioButtonsProps) {
  const childrenArray = Children.toArray(children);
  if (!childrenArray.every(child => (child as ReactElement).type === Item)) {
    throw new Error(`All children must be of type FancyRadioButtons.Item`);
  }
  return <h1>Fancy radio buttons</h1>
}

function Item({children}: ItemProps) {
  return <p>Button</p>
}

FancyRadioButtons.Item = Item
