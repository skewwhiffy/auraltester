import classNames from "classnames";
import { createContext, useContext } from "react";
import { PropsWithChildren, useState } from "react";

interface FancyRadioButtonsProps<T> extends PropsWithChildren {
  value?: T | null

  onChange(value: T): void
}

interface ItemProps<T> {
  value?: T
  children: string
}

interface ContextType<T> {
  value?: T | null
  setValue(value?: T | null): void
}

const Context = createContext<ContextType<any>>({
  value: null,
  setValue: (() => { })
});

const Item = <T extends any>({ children, value }: ItemProps<T>) => {
  const context = useContext(Context)
  const isSelected = (value ?? children) === context.value;
  return (
    <div
      className={classNames("p-4 flex text-center", {
        "hover:underline hover:cursor-pointer": !isSelected,
        "bg-gray-300": isSelected,
      })}
      onClick={() => context.setValue(value ?? children)}>
      {children}
    </div>
  )
}

const FancyRadioButtons = <T extends any>({ children, value, onChange }: FancyRadioButtonsProps<T>) => {
  const [selected, setSelected] = useState(value)
  const setValue = (value: T) => {
    setSelected(value)
    onChange(value)
  }
  return (
    <Context.Provider value={{ value: selected, setValue }}>
      <div className={classNames("flex gap-4 justify-between", {
        "bg-gray-50": value === null || value === undefined
      })}>
        {children}
      </div>
    </Context.Provider>
  )
}

FancyRadioButtons.Item = Item

export default FancyRadioButtons