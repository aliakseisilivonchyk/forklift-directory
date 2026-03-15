export interface Malfunction {
    id?: bigint
    startTimestamp: string
    endTimestamp?: string
    downtime?: number
    description?: string
    isNew: boolean
}