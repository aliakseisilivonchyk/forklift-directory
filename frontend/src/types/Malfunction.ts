export interface Malfunction {
    id?: number
    startTimestamp: string
    endTimestamp?: string
    downtime?: number
    description?: string
    isNew: boolean
}