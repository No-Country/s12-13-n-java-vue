package com.latam.unamano.service.chatRoom;


import com.latam.unamano.dto.chatRoom.ChatRoomDto;
import com.latam.unamano.dto.auth.UserResponseDto;
import com.latam.unamano.dto.chatRoom.ChatRoomResponseDto;
import com.latam.unamano.dto.clientDto.response.GetClient;
import com.latam.unamano.dto.workerDto.reponse.GetWorker;
import com.latam.unamano.exceptions.NoExistingChatRoomsException;
import com.latam.unamano.persistence.entities.chatRoom.ChatRoom;
import com.latam.unamano.persistence.entities.client.Client;
import com.latam.unamano.persistence.entities.workerEntity.Worker;
import com.latam.unamano.persistence.repositories.chatRoom.ChatRoomRepository;
import com.latam.unamano.service.auth.AuthService;
import com.latam.unamano.service.clientService.ClientService;
import com.latam.unamano.service.workerService.WorkerService;
import com.latam.unamano.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final AuthService authService;
    private final ClientService clientService;
    private final WorkerService workerService;
    public void createChatRoom(String name, Client client, Worker worker){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(name);
        chatRoom.setClient(client);
        chatRoom.setWorker(worker);
        chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoomResponseDto>  getAllChatsRoomByUserid(){
        UserResponseDto userResponseDto = authService.getUserDetails();
        if(userResponseDto.role() == Role.ROLE_CLIENT){
           Optional<GetClient> clientOptional = clientService.getByUserId(userResponseDto.id());
            if(clientOptional.isPresent()){
                GetClient clientDto = clientOptional.get();
                List<ChatRoom> chatRooms = chatRoomRepository.getAllChatsRoomByClientId(clientDto.id_client());
                return chatRooms.stream().map(ChatRoomResponseDto::new).toList();
            }
        }
        if(userResponseDto.role() == Role.ROLE_WORKER){
            Optional<GetWorker> workerOptional =  workerService.getByUserId(userResponseDto.id());
            if(workerOptional.isPresent()){
                GetWorker workerDto = workerOptional.get();
                List<ChatRoom> chatRooms = chatRoomRepository.getAllChatsRoomByWorkerId(workerDto.id_worker());
                return chatRooms.stream().map(ChatRoomResponseDto::new).toList();
            }
        }
        throw new NoExistingChatRoomsException("No hay chats");
    }


    public ChatRoom getChatRoomById(Long chatRoomId) {
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(chatRoomId);
        if (chatRoomOptional.isPresent()){
            return chatRoomOptional.get();
        }
        throw new RuntimeException("No existe esta sala de chat");
    }
}
