package r2z1.construct.a.dec

import kotlinx.serialization.Serializable

@Serializable
data class ChatbotMessage(
    val id: Int,
    val botId: String,
    val userId: String,
    val message: String,
    val timestamp: Long
)

@Serializable
data class Chatbot(
    val id: String,
    val name: String,
    val description: String,
    val messages: MutableList<ChatbotMessage> = mutableListOf()
)

@Serializable
data class MonitorSettings(
    val timeout: Long,
    val maxMessageCount: Int
)

@Serializable
data class DecentralizedChatbotMonitor(
    val chatbots: MutableMap<String, Chatbot> = mutableMapOf(),
    val monitorSettings: MonitorSettings,
    val nodePublicKey: String,
    val blockchainUrl: String
)

@Serializable
data class NodeData(
    val nodeId: String,
    val publicKey: String,
    val chatbotMonitors: MutableList<DecentralizedChatbotMonitor> = mutableListOf()
)

@Serializable
data class BlockchainNode(
    val nodeId: String,
    val nodeData: NodeData,
    val connectedNodes: MutableList<String> = mutableListOf()
)

fun main() {
    // Initialize chatbot monitors
    val monitor1 = DecentralizedChatbotMonitor(
        mapOf("bot1" to Chatbot("bot1", "Bot 1", "Description 1")),
        MonitorSettings(30000, 100),
        "node1-public-key",
        "https://blockchain-node-1.com"
    )

    val monitor2 = DecentralizedChatbotMonitor(
        mapOf("bot2" to Chatbot("bot2", "Bot 2", "Description 2")),
        MonitorSettings(30000, 100),
        "node2-public-key",
        "https://blockchain-node-2.com"
    )

    // Initialize blockchain nodes
    val node1 = BlockchainNode("node1", NodeData("node1", "node1-public-key", mutableListOf(monitor1)))
    val node2 = BlockchainNode("node2", NodeData("node2", "node2-public-key", mutableListOf(monitor2)))

    // Connect nodes
    node1.connectedNodes.add("node2")
    node2.connectedNodes.add("node1")

    // Start monitors
    // ...
}