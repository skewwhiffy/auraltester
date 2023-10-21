import classNames from "classnames";
import { createContext, useContext } from "react";
import { PropsWithChildren, useState } from "react";

interface FancyRadioButtonsProps extends PropsWithChildren {
  value?: string | null

  onChange(value: string): void
}

interface ItemProps {
  value?: string
  children: string
}

const Context = createContext({
  value: null as string | null | undefined,
  setValue: (() => { }) as (selected: string) => void
});

const Item = ({ children, value }: ItemProps) => {
  const context = useContext(Context)
  const isSelected = value === context.value;
  return (
    <div
      className={classNames("p-8 flex text-center", {
        "hover:underline hover:cursor-pointer": !isSelected,
        "bg-gray-300": isSelected,
      })}
      onClick={() => context.setValue(value ?? children)}>
      {children}
    </div>
  )
}

const FancyRadioButtons = ({ children, value, onChange }: FancyRadioButtonsProps) => {
  const [selected, setSelected] = useState(value)
  const setValue = (value: string) => {
    setSelected(value)
    onChange(value)
  }
  return (
    <Context.Provider value={{ value: selected, setValue }}>
      <div className="flex gap-4 justify-between">
        {children}
      </div>
    </Context.Provider>
  )
}

FancyRadioButtons.Item = Item

export default FancyRadioButtons