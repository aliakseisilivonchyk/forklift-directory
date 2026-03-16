export interface Forklift {
    id?: number
    brand: string
    number: string
    carryingCapacity: number
    isActive: boolean
    updateTimestamp?: string
    appUser?: string
    isNew: boolean
    isEdited: boolean
}