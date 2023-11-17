package com.chat.websocket;

import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import com.chat.websocket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class WebsocketApplication {

	@Autowired
	private ContactService contactService;
	@Autowired
	private ConversationService conversationService;
	@Autowired
	private GroupMemberService groupMemberService;
	@Autowired
	private MessageService messageService;

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}

	//
	// Message
	//
	@Bean
	public CommandLineRunner commandLineRunnerMessage() {

		return runner -> {
			// Find all
//			List<Message> messages = messageService.findAll();
//
//			for (Message message :
//					messages) {
//				System.out.println(message.toString());
//			}

			// Find by id
//			Message message = messageService.findByID(1);
//			System.out.println("Contact: " + message);

			// Save new message
//			Conversation conversation = conversationService.findByID(1);
//
//			Message message = new Message();
//			message.setMessageContent("Alo, ranh khong Tuyen");
//			message.setSenderID(1);
//			message.setReceiverID(2);
//			message.setConversation(conversation);
//
//			messageService.save(message);

			// Delete message by id
//			messageService.deleteByID(7);

			// Find messages by conversation id
//			List<Message> messages = messageService.findMessageByConversationID(1);
//
//			for (Message message:
//				 messages) {
//				System.out.println(message.toString());
//			}

			// Find most recent message by conversation id
//			Message message = messageService.findLatestMessageByConversationID(1);
//			System.out.println("Message: " + message);



		};
	}

	//
	// GroupMember
	//
	@Bean
	public CommandLineRunner commandLineRunnerGroupMember() {

		return runner -> {
			// Find all
//			List<GroupMember> groupMembers = groupMemberService.findAll();
//
//			for (GroupMember groupMember :
//					groupMembers) {
//				System.out.println(groupMember.toString());
//			}

			// Find by id
//			GroupMember groupMember = groupMemberService.findByID(1);
//			System.out.println("GroupMember: "+groupMember);

			// Save new group member
//			Conversation conversation = conversationService.findByID(1);
//			Contact contact = contactService.findByID(2);
//
//			GroupMember groupMember = new GroupMember();
//			groupMember.setContact(contact);
//			groupMember.setConversation(conversation);
//
//			groupMemberService.save(groupMember);

			// Delete group member by id
//			groupMemberService.deleteByID(7);

			// Find group members by contact id
//			List<GroupMember> groupMembers = groupMemberService.findGroupMembersByContactID(1);
//
//			for (GroupMember groupMember :
//					groupMembers) {
//				System.out.println("Group member: " + groupMember);
//			}

			// Find groupMembers by conversation id
//			List<GroupMember> groupMembers = groupMemberService.findGroupMembersByConversationID(1);
//
//			for (GroupMember groupMember :
//					groupMembers) {
//				System.out.println("Group member: " + groupMember);
//			}

		};
	}

	//
	// Contact
	//
	@Bean
	public CommandLineRunner commandLineRunnerContact() {

		return runner -> {
			// Find all
//			List<Contact> contacts = contactService.findAll();
//
//			for (Contact contact :
//					contacts) {
//				System.out.println(contact.toString());
//			}

			// Find by id
//			Contact contact = contactService.findByID(10);
//			System.out.println("Contact: " + contact);

			// Save new contact
//			Contact contact = new Contact();
//			contact.setFirstName("Doanh");
//			contact.setLastName("Tran");
//			contact.setEmail("doanh@gmail.com");
//
//			contactService.save(contact);

			// Delete contact by id
//			Contact contact = contactService.deleteByID(6);
//			System.out.println("Contact deleteted: "+contact);

			// Find contact by group member id
//			Contact contact = contactService.findContactByGroupMemberID(5);
//			System.out.println("Contact: " + contact);

			// Find contacts by conversation id
//			List<Contact> contacts = contactService.findContactsByConversationID(1);
//
//			for (Contact contact :
//					contacts) {
//				System.out.println("Contact:" +contact.toString());
//			}
		};
	}


	//
	// Conversation
	//
	@Bean
	public CommandLineRunner commandLineRunnerConversation() {

		return runner -> {
			// Find all
//			List<Conversation> conversations = conversationService.findAll();
//
//			for (Conversation conversation :
//					conversations) {
//				System.out.println(conversation.toString());
//			}

			// Find by id
//			Conversation conversation = conversationService.findByID(1);
//			System.out.println("Contact: " + conversation);

			// Save new conversation
//			Conversation conversation = new Conversation();
//			conversation.setConversationName("Nhóm TM");
//			conversation.setConversationType(ConversationType.OneToOne);
//
//			conversationService.save(conversation);


			// Delete conversation by id
//			conversationService.deleteByID(2);

			// Find conversation by contact id
//				List<Conversation>  conversations = conversationService.findConversationsByContactID(1);
//				for (Conversation conversation :
//						conversations) {
//					System.out.println("Conversation: " + conversation);
//				}

			// Find conversation by group member id
//			Conversation conversation = conversationService.findConversationByGroupMemberID(1);
//			System.out.println("Conversation: "+conversation);

			// Create conversation with group member
//			Conversation conversation = new Conversation();
//			conversation.setConversationName("Nhom ");
		};
	}
}
