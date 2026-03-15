export interface Forklift {
    id?: bigint
    brand: string
    number: string
    carryingCapacity: number
    isActive: boolean
    updateTimestamp?: string
    appUser?: string
    isNew: boolean
    isEdited: boolean
}